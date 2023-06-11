package com.HemlockStudiosWebsite.events;

import com.HemlockStudiosWebsite.entity.News;

public class NewsMadeEvent {
    private final News newsMade;

    public NewsMadeEvent(News newsMade) {
        this.newsMade = newsMade;
    }

    public News getNewsMade() {
        return newsMade;
    }
}
