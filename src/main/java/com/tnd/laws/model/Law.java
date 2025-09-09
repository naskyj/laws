package com.tnd.laws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tnd.laws.dto.LawDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Law {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @JsonIgnore
    private Long lawId;
    private String title;
    private String description;
    private String titleYoruba;
    private String descriptionYoruba;
    private LocalDate dateEnacted;
    private boolean translated = false;
    private boolean common = false;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Date createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "law", fetch=FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private
    Set<Chapter> chapters;

    public Law(LawDTO dto) {
        this.title = dto.getTitle();
        this.description = dto.getDescription();
        this.dateEnacted = dto.getDateEnacted();
        this.common = dto.isCommon();
    }
}
