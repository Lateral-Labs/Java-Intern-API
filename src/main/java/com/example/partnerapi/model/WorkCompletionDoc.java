package com.example.partnerapi.model;

import com.example.partnerapi.dto.completeWorkDTO.WorkCompletionDocDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "work_completion_doc")
public class WorkCompletionDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "body")
    private String body;

    @ManyToOne
    @JsonBackReference(value = "application-workCompletionDoc")
    @JoinColumn(name = "application_id")
    private Application application;

    public WorkCompletionDoc() {
    }

    public WorkCompletionDoc(WorkCompletionDocDTO workCompletionDocDTO, Application application) {
        this.fileName = workCompletionDocDTO.getFileName();
        this.body = workCompletionDocDTO.getBody();
        this.application = application;
    }
}
