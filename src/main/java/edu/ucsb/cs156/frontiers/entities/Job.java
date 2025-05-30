package edu.ucsb.cs156.frontiers.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.ZonedDateTime;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity(name = "jobs")
@EntityListeners(AuditingEntityListener.class)
public class Job {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "created_by_id")
  @ToString.Exclude
  private User createdBy;

  @CreatedDate private ZonedDateTime createdAt;
  @LastModifiedDate private ZonedDateTime updatedAt;

  private String status;

  // 1048576 is 2^20, which is the max size of a mediumtext in MySQL
  @Column(
      columnDefinition = "TEXT",
      length = 1048576) // needed for long strings, i.e. log entries longer than 255
  // characters
  private String log;
}
