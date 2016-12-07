package com.epam.tc.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CourseId")
    private int id;
    private String name;
    private String description;
    private String links;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "subscribers", joinColumns = {
        @JoinColumn(name = "CourseId")}, inverseJoinColumns = {
        @JoinColumn(name = "UserId")})
    private Set<User> subscribers;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Evaluate> attenders;

    @ManyToOne
    @JoinColumn(name = "decisionId")
    private Decision decision;

    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    public Decision getDecision() {
        return decision;
    }

    @ManyToOne
    @JoinColumn(name = "UserId")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "StatusId")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isSubscribed(User user) {
        return subscribers.contains(user);
    }

    public boolean isAttended(User user) {
        return attenders.stream().anyMatch((attender) -> (attender.getUser() == user));
    }

    public boolean hasGrade(User user) {
        return attenders.stream().anyMatch((attender)
                -> ((attender.getUser() == user) && (attender.getGrade() != null)));
    }

    public double getAverageGrade() {
        double count = 0;
        double sum = 0;
        for (Evaluate attender : attenders) {
            if (attender.getGrade() != null) {
                sum += attender.getGrade();
                count++;
            }
        }
        if (count == 0) {
            return 0;
        } else {
            return sum / count;
        }
    }

    public Set<User> getSubscribers() {
        return this.subscribers;
    }

    public Set<Evaluate> getAttenders() {
        return attenders;
    }

    public void setAttenders(Set<Evaluate> attenders) {
        this.attenders = attenders;
    }

    public void addSubscriber(User subscriber) {
        this.subscribers.add(subscriber);
    }

    public void deleteSubscriber(User subscriber) {
        this.subscribers.remove(subscriber);
    }

    public void addAttender(User attender) {
        Evaluate evaluate = new Evaluate(attender, this);
        this.attenders.add(evaluate);
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public Course(String name, String description, String links, User user) {
        this.name = name;
        this.description = description;
        this.links = links;
        this.owner = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getLinks() {
        return links;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLinks(String links) {
        this.links = links;
    }
}
