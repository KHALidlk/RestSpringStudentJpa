//package org.example.jspspring.Model;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.IdClass;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//
//import java.io.Serializable;
//@Data
//@AllArgsConstructor
//@Entity
//@Table(name = "authorities")
//@IdClass(Authority.AuthorityId.class)
//public class Authority {
//
//    @Id
//    @Column(name = "username", length = 50, nullable = false)
//    private String username;
//
//    @Id
//    @Column(name = "authority", length = 50, nullable = false)
//    private String authority;
//
//    @ManyToOne
//    @JoinColumn(name = "name", referencedColumnName = "username", insertable = false, updatable = false)
//    private Student user;
//
//    // Default constructor
//    public Authority() {
//    }
//
//    // Constructor with fields
//    public Authority(String username, String authority) {
//        this.username = username;
//        this.authority = authority;
//    }
//
//
//
//    // Composite key class
//    public static class AuthorityId implements Serializable {
//        private String username;
//        private String authority;
//
//        public AuthorityId() {
//        }
//
//        public AuthorityId(String username, String authority) {
//            this.username = username;
//            this.authority = authority;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            AuthorityId that = (AuthorityId) o;
//            return username.equals(that.username) && authority.equals(that.authority);
//        }
//
//        @Override
//        public int hashCode() {
//            return 31 * username.hashCode() + authority.hashCode();
//        }
//    }
//}