package com.bingyan.pixcelcity.bean.currentUser;

public class Option {

    //notice

    boolean badge_get;
    boolean badge_progress;
    boolean be_explored;
    boolean be_wanted;
    boolean explore_more;

    //privacy
    boolean explore_visible;
    boolean post_visible;
    boolean want_visible;

    public Option() {
    }

    public Option(boolean badge_get, boolean badge_progress, boolean be_explored, boolean be_wanted, boolean explore_more) {
        this.badge_get = badge_get;
        this.badge_progress = badge_progress;
        this.be_explored = be_explored;
        this.be_wanted = be_wanted;
        this.explore_more = explore_more;
    }

    public Option(boolean explore_visible, boolean post_visible, boolean want_visible) {
        this.explore_visible = explore_visible;
        this.post_visible = post_visible;
        this.want_visible = want_visible;
    }

    public Option(boolean badge_get, boolean badge_progress, boolean be_explored, boolean be_wanted, boolean explore_more, boolean explore_visible, boolean post_visible, boolean want_visible) {
        this.badge_get = badge_get;
        this.badge_progress = badge_progress;
        this.be_explored = be_explored;
        this.be_wanted = be_wanted;
        this.explore_more = explore_more;
        this.explore_visible = explore_visible;
        this.post_visible = post_visible;
        this.want_visible = want_visible;
    }

    public boolean isBadge_get() {
        return badge_get;
    }

    public void setBadge_get(boolean badge_get) {
        this.badge_get = badge_get;
    }

    public boolean isBadge_progress() {
        return badge_progress;
    }

    public void setBadge_progress(boolean badge_progress) {
        this.badge_progress = badge_progress;
    }

    public boolean isBe_explored() {
        return be_explored;
    }

    public void setBe_explored(boolean be_explored) {
        this.be_explored = be_explored;
    }

    public boolean isBe_wanted() {
        return be_wanted;
    }

    public void setBe_wanted(boolean be_wanted) {
        this.be_wanted = be_wanted;
    }

    public boolean isExplore_more() {
        return explore_more;
    }

    public void setExplore_more(boolean explore_more) {
        this.explore_more = explore_more;
    }

    public boolean isExplore_visible() {
        return explore_visible;
    }

    public void setExplore_visible(boolean explore_visible) {
        this.explore_visible = explore_visible;
    }

    public boolean isPost_visible() {
        return post_visible;
    }

    public void setPost_visible(boolean post_visible) {
        this.post_visible = post_visible;
    }

    public boolean isWant_visible() {
        return want_visible;
    }

    public void setWant_visible(boolean want_visible) {
        this.want_visible = want_visible;
    }
}
