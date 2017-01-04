package com.swarmnyc.mvvmlib.sampleapp.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.agera.Function;
import com.google.android.agera.Merger;
import com.google.android.agera.Receiver;
import com.google.android.agera.Repositories;
import com.google.android.agera.Repository;
import com.google.android.agera.Result;
import com.google.android.agera.Updatable;
import com.swarmnyc.mvvmlib.MvvmViewModel;
import com.swarmnyc.mvvmlib.binding.BindableString;
import com.swarmnyc.mvvmlib.sampleapp.model.Episode;
import com.swarmnyc.mvvmlib.sampleapp.services.DSExecutor;
import com.swarmnyc.mvvmlib.sampleapp.services.EpisodesSupplier;
import com.swarmnyc.mvvmlib.binding.ObservableEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tao on 12/29/16.
 */
public class RecyclerViewModel extends MvvmViewModel {
    final String TAG=getClass().getName();
    public static final Parcelable.Creator<RecyclerViewModel> CREATOR = new Creator<RecyclerViewModel>() {
        @Override
        public RecyclerViewModel createFromParcel(Parcel source) {
            return new RecyclerViewModel(source);
        }

        @Override
        public RecyclerViewModel[] newArray(int size) {
            return new RecyclerViewModel[size];
        }
    };

    private ObservableArrayList<EpisodeViewModel> mEpisodes = new ObservableArrayList<>();
    private ObservableArrayList<EpisodeViewModel> mFilteredEpisodes = new ObservableArrayList<>();
    private ObservableEvent mRefresh = new ObservableEvent();
    private BindableString mDescription = new BindableString("");
    private BindableString mSearchKeyword = new BindableString("");

    private Repository<Result<List<Episode>>> mAllEpisodeRepository;
    private Repository<Result<List<Episode>>> mFilteredEpisodeRepository;

    private Updatable mDescriptionUpdatable = new Updatable() {
        @Override
        public void update() {
            mDescription.set(String.format("Total: %d; Filtered by '%s': %d", mEpisodes.size(), mSearchKeyword.get(), mFilteredEpisodes.size()));
        }
    };
    private Updatable mFilteredEpisodeUpdatable = new Updatable() {
        @Override
        public void update() {
            mFilteredEpisodeRepository.get()
                    .ifSucceededSendTo(new Receiver<List<Episode>>() {
                        @Override
                        public void accept(@NonNull List<Episode> value) {
                            mFilteredEpisodes.clear();
                            for (Episode e : value) {
                                mFilteredEpisodes.add( new EpisodeViewModel(e));
                            }
                        }
                    });
        }
    };
    private Updatable mEpisodeUpdatable = new Updatable() {
        @Override
        public void update() {
            mAllEpisodeRepository.get().ifFailedSendTo(new Receiver<Throwable>() {
                @Override
                public void accept(@NonNull Throwable value) {
                    Toast.makeText(getContext().getAndroidContext(), "Network Failure:" + value.toString(), Toast.LENGTH_SHORT).show();
                }
            }).ifSucceededSendTo(new Receiver<List<Episode>>() {
                @Override
                public void accept(@NonNull List<Episode> value) {
                    mEpisodes.clear();
                    for (Episode e : value) {
                        mEpisodes.add(new EpisodeViewModel(e));
                    }
                }
            });
        }
    };
    private Updatable mRefreshUpdatable = new Updatable() {
        @Override
        public void update() {
            mRefresh.finish();
        }
    };


    public RecyclerViewModel() {
    }

    protected RecyclerViewModel(Parcel in) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    @Override
    public void onInit(Bundle args) {
        mAllEpisodeRepository = Repositories.repositoryWithInitialValue(Result.<List<Episode>>absent())
                .observe(mRefresh)
                .onUpdatesPerLoop()
                .goTo(DSExecutor.EXECUTOR)
                .mergeIn(new EpisodesSupplier(), new Merger<Result<List<Episode>>, Result<List<Episode>>, Result<List<Episode>>>() {
                    @NonNull
                    @Override
                    public Result<List<Episode>> merge(@NonNull Result<List<Episode>> listResult, @NonNull Result<List<Episode>> listResult2) {
                        if (!listResult.isPresent()) return listResult2;
                        if (listResult2.isPresent()) listResult2.get().addAll(listResult.get());
                        return listResult2;
                    }
                })
                .thenTransform(new Function<Result<List<Episode>>, Result<List<Episode>>>() {
                    @NonNull
                    @Override
                    public Result<List<Episode>> apply(@NonNull Result<List<Episode>> input) {
                        return input;
                    }
                })
                .compile();

        mAllEpisodeRepository.addUpdatable(mEpisodeUpdatable);
        mAllEpisodeRepository.addUpdatable(mRefreshUpdatable);
        mAllEpisodeRepository.addUpdatable(mDescriptionUpdatable);
        mRefresh.start();

        mFilteredEpisodeRepository = Repositories.repositoryWithInitialValue(Result.<List<Episode>>absent())
                .observe(mAllEpisodeRepository, mSearchKeyword)
                .onUpdatesPerLoop()
                .goTo(DSExecutor.EXECUTOR)
                .attemptGetFrom(mAllEpisodeRepository).orEnd(new Function<Throwable, Result<List<Episode>>>() {
                    @NonNull
                    @Override
                    public Result<List<Episode>> apply(@NonNull Throwable input) {
                        return Result.failure();
                    }
                })
                .thenTransform(new Function<List<Episode>, Result<List<Episode>>>() {
                    @NonNull
                    @Override
                    public Result<List<Episode>> apply(@NonNull List<Episode> input) {
                        if (mSearchKeyword.get().length() > 0) {
                            List<Episode> filteredEpisodes = new ArrayList<Episode>();
                            for ( Episode e : input) {
                                if (e.title.contains(mSearchKeyword.get())) filteredEpisodes.add(e);
                            }
                            return Result.present(filteredEpisodes);
                        } else {
                            return Result.present(input);
                        }
                    }

                })
                .compile();
        mFilteredEpisodeRepository.addUpdatable(mFilteredEpisodeUpdatable);
        mFilteredEpisodeRepository.addUpdatable(mDescriptionUpdatable);

        mSearchKeyword.addUpdatable(mDescriptionUpdatable);
    }

    public ObservableArrayList getAllEpisodes() {
        return mEpisodes;
    }

    public ObservableArrayList getFilteredEpisodes () {
        return mFilteredEpisodes;
    };

    public ObservableBoolean getIsLoading() {
        return mRefresh.get();
    }

    public ObservableEvent getRefresh() {
        return mRefresh;
    }

    public BindableString getSearchKeyword() { return mSearchKeyword; }

    public BindableString getDescription() {
        return mDescription;
    }
}
