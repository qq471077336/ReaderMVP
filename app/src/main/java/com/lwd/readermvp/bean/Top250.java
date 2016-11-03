package com.lwd.readermvp.bean;

import java.util.List;

/**
 * Created by lianweidong on 2016/10/15.
 */

public class Top250 {
    public int           count;
    public int           start;
    public List<subject> subjects;
    public String        title;
    public int           total;

    public class subject {
        public String          alt;
        public List<Character> casts;
        public int             collect_count;
        public List<Character> directors;
        public String[]        genres;
        public String          id;
        public Images          images;
        public String          original_title;
        public Rating          rating;
        public String          subtype;
        public String          title;
        public String          year;
    }

    public class Images {
        public String large;
        public String medium;
        public String small;
    }

    public class Rating {
        public float  average;
        public int    max;
        public int    min;
        public String stars;
    }

    public class Character {
        public String alt;
        public Images avatars;
        public String id;
        public String name;
    }
}
