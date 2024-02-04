package com.courseapi.domain.entities;

import java.util.ArrayList;

public class StokeEntryCalculator {

  static public int exec(ArrayList<StokeEntry> stokeEntries) {
    int total = 0;
    for (StokeEntry stokeEntry : stokeEntries) {
      total = stokeEntry.calc(total);
    }
    return total;
  }

}
