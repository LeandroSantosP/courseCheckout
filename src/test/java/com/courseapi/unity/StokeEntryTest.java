package com.courseapi.unity;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.courseapi.domain.entities.StokeEntry;
import com.courseapi.domain.entities.StokeEntryCalculator;
import com.courseapi.domain.entities.StokeEntryIn;
import com.courseapi.domain.entities.StokeEntryOut;

@SpringBootTest
public class StokeEntryTest {

  @Test
  void shouldBeAbleCreateAnStokeEntry() {
    StokeEntry stokeEntry = new StokeEntryIn("productId", 10);
    assertEquals(stokeEntry.getProductId(), "productId");
    assertEquals(stokeEntry.getOperation(), "in");
    assertEquals(stokeEntry.getAmount(), 10);
  }

  @Test
  void shouldNotBeAbleCreateAnStokeEntryWithNegativeAmount() {

    try {
      new StokeEntryOut("productId", -10);
    } catch (Exception e) {
      assertEquals(e.getMessage(), "Amount cannot be negative");
    }
  }

  @Test
  void shouldBeAbleCalculateStokeEntriesAmount() {
    ArrayList<StokeEntry> stokeEntries = new ArrayList<>(Arrays.asList(
        new StokeEntryIn("productId1", 10),
        new StokeEntryOut("productId2", 5),
        new StokeEntryIn("productId3", 30),
        new StokeEntryOut("productId4", 10)));

    StokeEntryCalculator stokeEntryCalculator = new StokeEntryCalculator();

    int result = stokeEntryCalculator.exec(stokeEntries);

    assertEquals(result, 25);

  }

}
