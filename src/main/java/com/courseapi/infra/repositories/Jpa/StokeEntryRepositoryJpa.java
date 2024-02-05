package com.courseapi.infra.repositories.Jpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.courseapi.application.repositories.StokeEntryRepository;
import com.courseapi.domain.entities.StokeEntry;
import com.courseapi.domain.entities.StokeEntryFactory;
import com.courseapi.infra.repositories.Jpa.JpaTableObjScan.StokeEntryJpa;

@Repository
public interface StokeEntryRepositoryJpa extends JpaRepository<StokeEntryJpa, String>, StokeEntryRepository {
  List<StokeEntryJpa> findByProductId(String id);

  @Override
  default String save(StokeEntry stokeEntry) {
    var couseData = this
        .save(new StokeEntryJpa(stokeEntry.getId(), stokeEntry.getProductId(), stokeEntry.getOperation(),
            stokeEntry.getAmount()));

    return couseData.getId();
  }

  @Override
  default StokeEntry get(String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'get'");
  }

  @Override
  default ArrayList<StokeEntry> getAllByProductId(String id) {

    List<StokeEntryJpa> stokeEntryJpas = this.findByProductId(id);
    ArrayList<StokeEntry> stokeEntries = new ArrayList<>();
    stokeEntryJpas.forEach(stokeData -> {
      var stokeEntry = StokeEntryFactory.rebuild(stokeData.getOperation(), stokeData.getId(), stokeData.getOperation(),
          stokeData.getAmount());
      stokeEntries.add(stokeEntry);
    });
    return stokeEntries;
  }

  @Override
  default int count(String id) {
    return getAllByProductId(id).size();
  }
}
