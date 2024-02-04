package com.courseapi.infra.repositories.Jpa.JpaTableObjScan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "orders")
@Entity(name = "order")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class OrderJpa {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(nullable = false)
  private String status;

  @Column(nullable = false)
  private double price;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String name;

  @ManyToOne
  @JoinColumn(name = "corse_id", nullable = false)
  private CourseJpa courseJpa;

}
