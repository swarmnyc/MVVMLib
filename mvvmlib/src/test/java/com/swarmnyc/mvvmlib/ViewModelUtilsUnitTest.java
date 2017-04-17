package com.swarmnyc.mvvmlib;

import com.swarmnyc.mvvmlib.Mocks.TestActivity1;
import com.swarmnyc.mvvmlib.Mocks.TestActivity2;
import com.swarmnyc.mvvmlib.Mocks.TestActivity4;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ViewModelUtilsUnitTest {

    @Test
    public void assignFromViewModelTest() throws Exception {
        assertTrue(ViewModelUtils.assignFromViewModel(TestActivity1.class));
        assertFalse(ViewModelUtils.assignFromViewModel(TestActivity2.class));
        assertTrue(ViewModelUtils.assignFromViewModel(TestActivity4.class));
    }

    @Test
    public void createViewModelTest() throws Exception {
        // regular usage
        assertNotNull(ViewModelUtils.createViewModel(TestActivity1.class));

        // Not support multiple extends without generic
        // assertNotNull(ViewModelUtils.createViewModel(TestActivity2.class));

        // support multiple extends with generic
        assertNotNull(ViewModelUtils.createViewModel(TestActivity4.class));
    }

}