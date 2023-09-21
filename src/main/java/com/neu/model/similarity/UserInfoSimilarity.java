package com.neu.model.similarity;

import org.apache.lucene.index.AtomicReaderContext;
import org.apache.lucene.index.FieldInvertState;
import org.apache.lucene.search.CollectionStatistics;
import org.apache.lucene.search.TermStatistics;
import org.apache.lucene.search.similarities.Similarity;

import java.io.IOException;

public class UserInfoSimilarity extends Similarity {
    public long computeNorm(FieldInvertState fieldInvertState) {
        return 0;
    }

    public SimWeight computeWeight(float v, CollectionStatistics collectionStatistics, TermStatistics... termStatistics) {
        return null;
    }

    public ExactSimScorer exactSimScorer(SimWeight simWeight, AtomicReaderContext atomicReaderContext) throws IOException {
        return null;
    }

    public SloppySimScorer sloppySimScorer(SimWeight simWeight, AtomicReaderContext atomicReaderContext) throws IOException {
        return null;
    }
}
