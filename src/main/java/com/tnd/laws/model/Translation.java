package com.tnd.laws.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Translation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long translationId;
    private String entityType; // Law, Chapter, Section, or Subsection
    private Long entityId; // Foreign key to the relevant entity
    private String language;
    private String translatedText;

}

