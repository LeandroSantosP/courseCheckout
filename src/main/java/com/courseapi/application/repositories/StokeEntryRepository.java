package com.courseapi.application.repositories;

import java.util.ArrayList;

import com.courseapi.domain.entities.StokeEntry;

public interface StokeEntryRepository {
  String save(StokeEntry stokeEntry);

  StokeEntry get(String id);

  ArrayList<StokeEntry> getAllByProductId(String id);
}
