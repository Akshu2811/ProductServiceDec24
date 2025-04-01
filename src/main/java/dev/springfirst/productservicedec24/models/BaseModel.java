package dev.springfirst.productservicedec24.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@MappedSuperclass
public class BaseModel implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
        private Date createdAt;
        private  Date lastModifiedAt;
        private boolean isDeleted;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public Date getLastModifiedAt() {
            return lastModifiedAt;
        }

        public void setLastModifiedAt(Date lastModifiedAt) {
            this.lastModifiedAt = lastModifiedAt;
        }

        public boolean isDeleted() {
            return isDeleted;
        }

        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
    }

