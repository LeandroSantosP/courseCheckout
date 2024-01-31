package com.courseapi.infra.repositories.JpaRepositories;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "course")
@Entity(name = "course")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class CourseJpa {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(nullable = false)
  private String name;

  @Size(max = 255)
  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private int duration;

  @Min(value = 0)
  @Column(nullable = false)
  private double price;

}
