package com.swarmnyc.mvvmlib;

public class MvvmFragmentContext extends MvvmContext {
    private MvvmContext parentContext;
    private MvvmFragmentWrapper mvvmFragment;

    public MvvmFragmentContext(MvvmFragmentWrapper mvvmFragment) {
        super(mvvmFragment.getContext());

        this.mvvmFragment = mvvmFragment;
        this.parentContext = getContext(mvvmFragment.getContext());
    }
}
