package com.gh.dto;


import lombok.Data;

import java.util.List;

@Data
public class PlayerInventorySaveData {
    private String Version;
    private QuickSelect QuickSelect;
    private List<EquippedItem> EquippedItems;
    private ItemInstanceManagerData ItemInstanceManagerData;

    private class QuickSelect {

        private String Version;

        private List<Slot> Slots;

        @Data
        private class Slot {
            private Integer ItemId;
            private Double Age;
        }
    }

    private class EquippedItem {

        private String ItemId;

        private List<Module> Modules;
        @Data
        private class Module {
            private ChannelWeights ChannelWeights;
            private String Version;
            private Integer ModuleId;
            private class ChannelWeights {
                private String x;
                private String y;
                private String z;
                private String w;
            }
        }
    }

    private class ItemInstanceManagerData {
        private String Version;
    }
}
