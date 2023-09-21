package com.neu.model.similarity;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.util.Collection;

public class CustomUserSimilarity implements UserSimilarity {
    public double userSimilarity(long l, long l1) throws TasteException {
        return 0;
    }

    public void setPreferenceInferrer(PreferenceInferrer preferenceInferrer) {

    }

    public void refresh(Collection<Refreshable> collection) {

    }
}
