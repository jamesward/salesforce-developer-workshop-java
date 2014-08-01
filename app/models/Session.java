package models;

import javax.persistence.*;

import play.db.ebean.*;

import java.util.Date;

@Entity
@Table(schema = "salesforce", name = "session__c")
public class Session extends Model {

    @Id
    public String Id;

    public String Name;

    public static Finder<Long, Session> find = new Finder<Long, Session>(Long.class, Session.class);

}