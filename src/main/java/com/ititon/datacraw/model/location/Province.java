package com.ititon.datacraw.model.location;


import com.ititon.datacraw.model.location.ProvinceRegions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum Province {

    GUIYANG("贵阳市") {
        private Integer regionNameMaxLength;
        private Integer regionNameMinLength;
        private final Map<String, String> regions = new HashMap<>();
        {
            for (Guiyang region : Guiyang.values()) {
                regions.put(region.getTranslation(), region.name());
            }

            List<Integer> collect = regions.keySet().stream().map(String::length).collect(Collectors.toList());
            regionNameMaxLength = collect.stream().mapToInt(v -> v).max().orElse(0);
            regionNameMinLength = collect.stream().mapToInt(v -> v).min().orElse(0);
        }

        @Override
        public Integer getMinRegionNameLength() {
            return regionNameMinLength;
        }

        @Override
        public Integer getMaxRegionNameLength() {
            return regionNameMaxLength;
        }

        public String findRegionName(final String cnName) {
            return regions.get(cnName);
        }
    },
    ZUNYI("遵义市") {
        private Integer regionNameMaxLength;
        private Integer regionNameMinLength;
        private final Map<String, String> regions = new HashMap<>();
        {
            for (Zunyi region : Zunyi.values()) {
                regions.put(region.getTranslation(), region.name());
            }

            List<Integer> collect = regions.keySet().stream().map(String::length).collect(Collectors.toList());
            regionNameMaxLength = collect.stream().mapToInt(v -> v).max().orElse(0);
            regionNameMinLength = collect.stream().mapToInt(v -> v).min().orElse(0);
        }
        @Override
        public Integer getMinRegionNameLength() {
            return regionNameMinLength;
        }

        @Override
        public Integer getMaxRegionNameLength() {
            return regionNameMaxLength;
        }

        public String findRegionName(final String cnName) {
            return regions.get(cnName);
        }
    },
    ANSHUN("安顺市") {
        private Integer regionNameMaxLength;
        private Integer regionNameMinLength;
        private final Map<String, String> regions = new HashMap<>();
        {
            for (Anshun region : Anshun.values()) {
                regions.put(region.getTranslation(), region.name());
            }
            List<Integer> collect = regions.keySet().stream().map(String::length).collect(Collectors.toList());
            regionNameMaxLength = collect.stream().mapToInt(v -> v).max().orElse(0);
            regionNameMinLength = collect.stream().mapToInt(v -> v).min().orElse(0);
        }

        @Override
        public Integer getMinRegionNameLength() {
            return regionNameMinLength;
        }

        @Override
        public Integer getMaxRegionNameLength() {
            return regionNameMaxLength;
        }

        public String findRegionName(final String cnName) {
            return regions.get(cnName);
        }
    },
    BIJIE("毕节市") {
        private Integer regionNameMaxLength;
        private Integer regionNameMinLength;
        private final Map<String, String> regions = new HashMap<>();
        {
            for (Bijie region : Bijie.values()) {
                regions.put(region.getTranslation(), region.name());
            }

            List<Integer> collect = regions.keySet().stream().map(String::length).collect(Collectors.toList());
            regionNameMaxLength = collect.stream().mapToInt(v -> v).max().orElse(0);
            regionNameMinLength = collect.stream().mapToInt(v -> v).min().orElse(0);

        }

        @Override
        public Integer getMinRegionNameLength() {
            return regionNameMinLength;
        }

        @Override
        public Integer getMaxRegionNameLength() {
            return regionNameMaxLength;
        }

        public String findRegionName(final String cnName) {
            return regions.get(cnName);
        }
    },
    TONGREN("铜仁市") {
        private Integer regionNameMaxLength;
        private Integer regionNameMinLength;
        private final Map<String, String> regions = new HashMap<>();
        {
            for (Tongren region : Tongren.values()) {
                regions.put(region.getTranslation(), region.name());
            }

            List<Integer> collect = regions.keySet().stream().map(String::length).collect(Collectors.toList());
            regionNameMaxLength = collect.stream().mapToInt(v -> v).max().orElse(0);
            regionNameMinLength = collect.stream().mapToInt(v -> v).min().orElse(0);

        }

        @Override
        public Integer getMinRegionNameLength() {
            return regionNameMinLength;
        }

        @Override
        public Integer getMaxRegionNameLength() {
            return regionNameMaxLength;
        }

        public String findRegionName(final String cnName) {
            return regions.get(cnName);
        }
    },
    LIUPANSHUI("六盘水市") {
        private Integer regionNameMaxLength;
        private Integer regionNameMinLength;
        private final Map<String, String> regions = new HashMap<>();
        {
            for (Liupanshui region : Liupanshui.values()) {
                regions.put(region.getTranslation(), region.name());
            }

            List<Integer> collect = regions.keySet().stream().map(String::length).collect(Collectors.toList());
            regionNameMaxLength = collect.stream().mapToInt(v -> v).max().orElse(0);
            regionNameMinLength = collect.stream().mapToInt(v -> v).min().orElse(0);

        }

        @Override
        public Integer getMinRegionNameLength() {
            return regionNameMinLength;
        }

        @Override
        public Integer getMaxRegionNameLength() {
            return regionNameMaxLength;
        }

        public String findRegionName(final String cnName) {
            return regions.get(cnName);
        }
    },
    QIANXINAN("黔西南布依族苗族自治州") {
        private Integer regionNameMaxLength;
        private Integer regionNameMinLength;
        private final Map<String, String> regions = new HashMap<>();
        {
            for (Qianxinan region : Qianxinan.values()) {
                regions.put(region.getTranslation(), region.name());
            }

            List<Integer> collect = regions.keySet().stream().map(String::length).collect(Collectors.toList());
            regionNameMaxLength = collect.stream().mapToInt(v -> v).max().orElse(0);
            regionNameMinLength = collect.stream().mapToInt(v -> v).min().orElse(0);
        }

        @Override
        public Integer getMinRegionNameLength() {
            return regionNameMinLength;
        }

        @Override
        public Integer getMaxRegionNameLength() {
            return regionNameMaxLength;
        }

        public String findRegionName(final String cnName) {
            return regions.get(cnName);
        }
    },

    QIANDONGNAN("黔东南苗族侗族自治州") {
        private Integer regionNameMaxLength;
        private Integer regionNameMinLength;

        private final Map<String, String> regions = new HashMap<>();
        {
            for (Qiandongnan region : Qiandongnan.values()) {
                regions.put(region.getTranslation(), region.name());
            }
            List<Integer> collect = regions.keySet().stream().map(String::length).collect(Collectors.toList());
            regionNameMaxLength = collect.stream().mapToInt(v -> v).max().orElse(0);
            regionNameMinLength = collect.stream().mapToInt(v -> v).min().orElse(0);

        }

        @Override
        public Integer getMinRegionNameLength() {
            return regionNameMinLength;
        }

        @Override
        public Integer getMaxRegionNameLength() {
            return regionNameMaxLength;
        }

        public String findRegionName(final String cnName) {
            return regions.get(cnName);
        }
    },
    QIANNAN("黔南布依族苗族自治州") {

        private final Map<String, String> regions = new HashMap<>();
        private Integer regionNameMaxLength;
        private Integer regionNameMinLength;
        {
            for (Anshun region : Anshun.values()) {
                regions.put(region.getTranslation(), region.name());
            }

            List<Integer> collect = regions.keySet().stream().map(String::length).collect(Collectors.toList());
            regionNameMaxLength = collect.stream().mapToInt(v -> v).max().orElse(0);
            regionNameMinLength = collect.stream().mapToInt(v -> v).min().orElse(0);

        }

        @Override
        public Integer getMinRegionNameLength() {
            return regionNameMinLength;
        }

        @Override
        public Integer getMaxRegionNameLength() {
            return regionNameMaxLength;
        }

        public String findRegionName(final String cnName) {
            return regions.get(cnName);
        }
    },
    GUIAN("贵安新区") {

        private final Map<String, String> regions = new HashMap<>();
        private Integer regionNameMaxLength;
        private Integer regionNameMinLength;
        {
            for (Guian region : Guian.values()) {
                regions.put(region.getTranslation(), region.name());
            }

            List<Integer> collect = regions.keySet().stream().map(String::length).collect(Collectors.toList());
            regionNameMaxLength = collect.stream().mapToInt(v -> v).max().orElse(0);
            regionNameMinLength = collect.stream().mapToInt(v -> v).min().orElse(0);

        }

        @Override
        public Integer getMinRegionNameLength() {
            return regionNameMinLength;
        }

        @Override
        public Integer getMaxRegionNameLength() {
            return regionNameMaxLength;
        }

        public String findRegionName(final String cnName) {
            return regions.get(cnName);
        }
    },
    UNKNOWN("Unknown") {

        @Override
        public Integer getMinRegionNameLength() {
            return 0;
        }

        @Override
        public Integer getMaxRegionNameLength() {
            return 0;
        }

        public String findRegionName(final String cnName) {
            return "Unknown";
        }
    };

    private static final Map<String, Province> provinces = new HashMap<>();

    static {
        for (Province province : Province.values()) {
            provinces.put(province.getTranslation(), province);
        }
    }

    private String translation;

    Province(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }

    public static Province findProvince(final String cnProvinceName) {
        return provinces.get(cnProvinceName);
    }

    public abstract String findRegionName(final String cnName);

    public abstract Integer getMinRegionNameLength();

    public abstract Integer getMaxRegionNameLength();


}
