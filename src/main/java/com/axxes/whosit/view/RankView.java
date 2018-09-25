package com.axxes.whosit.view;

public class RankView {
    private int rank;
    private ScoreView best;
    private ScoreView current;

    public RankView() {
    }

    public RankView(int rank, ScoreView best) {
        this.rank = rank;
        this.best = best;
    }

    public RankView(int rank, ScoreView best, ScoreView current) {
        this.rank = rank;
        this.best = best;
        this.current = current;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public ScoreView getBest() {
        return best;
    }

    public void setBest(ScoreView best) {
        this.best = best;
    }

    public ScoreView getCurrent() {
        return current;
    }

    public void setCurrent(ScoreView current) {
        this.current = current;
    }
}
