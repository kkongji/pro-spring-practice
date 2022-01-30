package com.jun.prospring5.ch07.hibernateBase;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "singer")
@NamedQueries({
        @NamedQuery(name = "Singer.findAllWithAlbum",
                query = "select distinct s from Singer s " +
                        "left join fetch s.albums a " +
                        "left join fetch s.instruments i"),
        @NamedQuery(name="Singer.findById",
                query = "select distinct s from Singer s " +
                        "left join fetch s.albums a " +
                        "left join fetch s.instruments i " +
                        "where s.id = :id")
})
public class Singer implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private int version;
    private Set<Album> albums = new HashSet<>();
    private Set<Instrument> instruments = new HashSet<>();

    @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Album> getAlbums() {
        return albums;
    }

    @ManyToMany
    @JoinTable(name = "singer_instrument", joinColumns = @JoinColumn(name = "SINGER_ID"), inverseJoinColumns = @JoinColumn(name = "INSTRUMENT_ID"))
    public Set<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(Set<Instrument> instruments) {
        this.instruments = instruments;
    }

    public boolean addAlbum(Album album) {
        album.setSinger(this);
        return getAlbums().add(album);
    }

    public void removeAlbum(Album album) {
        getAlbums().remove(album);
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Version
    @Column(name = "VERSION")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", version=" + version +
                '}';
    }

    public boolean addInstrument(Instrument instrument) {
        return instruments.add(instrument);
    }
}
/*
 * 1. @Entity 해당 엔터티가 매핑된 엔터티 클래스임을 나타낸다.
 * 2. @Table Singer 엔터티 클래스가 매핑되어야 할 데이터베이스 테이블 이름을 정의한다.
 * 3. 컬럼 이름을 지정한 @Column 애너테이션을 적용한다.
 * 4. @Temporal(TemporalType.DATE) java.util.Date 타입을 SQL Date 타입으로 매핑하고 싶다는 뜻
 * 5. @Id 기본키라는 뜻
 * 6. @GeneratedValue, ID 생성 방법을 하이버네이트에 알려준다.
 * 7. @Version, version 애트리뷰트를 제어 수단으로 사용하는 낙관적 잠금(Optimistic Locking) 메커니즘을 사용한다. 하이버네이트가
 * 레코드 값을 수정할 때 마다 엔터티 인스턴스의 version과 데이터베이스 레코드의 version을 비교한다.
 * */
