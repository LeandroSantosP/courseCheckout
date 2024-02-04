package com.courseapi.unity;

import static org.junit.jupiter.api.Assertions.*;

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
    StokeEntry stokeEntry = StokeEntryIn.create("productId", 10);
    assertEquals(stokeEntry.getProductId(), "productId");
    assertEquals(stokeEntry.getOperation(), "in");
    assertEquals(stokeEntry.getAmount(), 10);
  }

  @Test
  void shouldNotBeAbleCreateAnStokeEntryWithNegativeAmount() {

    try {
      StokeEntryOut.create("productId", -10);
    } catch (Exception e) {
      assertEquals(e.getMessage(), "Amount cannot be negative");
    }
  }

  @Test
  void shouldBeAbleCalculateStokeEntriesAmount() {
    ArrayList<StokeEntry> stokeEntries = new ArrayList<>(Arrays.asList(
        StokeEntryIn.create("productId1", 10),
        StokeEntryOut.create("productId2", 5),
        StokeEntryIn.create("productId3", 30),
        StokeEntryOut.create("productId4", 10)));

    int result = StokeEntryCalculator.exec(stokeEntries);

    assertEquals(result, 25);

  }

}
