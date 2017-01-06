package com.swarmnyc.mvvmlib.sampleapp;

/**
 * Created by Tao on 12/20/16.
 */

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.util.Log;
import android.view.View;
import org.hamcrest.Matcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;

public final class OnDeviceScreenshotAction implements ViewAction {
    private final String tag;
    private final String testClass;
    private final String testMethod;
    private final String testLine;

    public OnDeviceScreenshotAction(String tag, String testClass, String testMethod, String testLine) {
        if (tag == null) this.tag="";
        else this.tag = tag;
        this.testClass = testClass;
        this.testMethod = testMethod;
        this.testLine = testLine;
    }

    @Override
    public Matcher<View> getConstraints() {
        return isDisplayed();
    }

    @Override public String getDescription() {
        return "Taking a screenshot using spoon.";
    }

    @Override public void perform(UiController uiController, View view) {
        takeScreenshot(testMethod, String.format("%s_%s", testLine, tag), getActivity(view));
    }

    public static void takeScreenshot(String folder, String name, Activity activity) {

        // In Testdroid Cloud, taken screenshots are always stored
        // under /test-screenshots/ folder and this ensures those screenshots
        // be shown under Test Results
        String path =
                Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + folder + File.separator;

        View scrView = activity.getWindow().getDecorView().getRootView();
        scrView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(scrView.getDrawingCache());
        scrView.setDrawingCacheEnabled(false);

        OutputStream out = null;
        File snapshotDirectory = new File(path);
        snapshotDirectory.mkdirs();
        File imageFile = new File(snapshotDirectory, name + ".png");

        try {
            out = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            addImageToGallery(imageFile.getAbsolutePath(), activity);
        } catch (FileNotFoundException e) {
            // exception
        } catch (IOException e) {
            // exception
        } finally {

            try {
                if (out != null) {
                    out.close();
                }

            } catch (Exception exc) {
            }

        }
    }
    public static void addImageToGallery(final String filePath, final Context context) {

        ContentValues values = new ContentValues();

        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
        values.put(MediaStore.MediaColumns.DATA, filePath);

        context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }

    private static Activity getActivity(View view) {
        Context context = view.getContext();
        while (!(context instanceof Activity)) {
            if (context instanceof ContextWrapper) {
                context = ((ContextWrapper) context).getBaseContext();
            } else {
                throw new IllegalStateException("Got a context of class "
                        + context.getClass()
                        + " and I don't know how to get the Activity from it");
            }
        }
        return (Activity) context;
    }

    /** This must be called directly from your test method. */
    public static void perform(String tag) {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        String testClass = trace[3].getClassName();
        String testMethod = trace[3].getMethodName();
        String testLine = Integer.toString(trace[3].getLineNumber());
        onView(isRoot()).perform(new OnDeviceScreenshotAction(tag, testClass, testMethod, testLine));
    }
}
