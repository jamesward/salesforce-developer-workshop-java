package models;

import javax.persistence.*;

import play.db.ebean.*;

import java.util.Date;

@Entity
public class Session extends Model {

    @Id
    public String Id;

    public String Name;

    public Date Session_Date__c;

    public static Finder<Long, Session> find = new Finder<Long, Session>(Long.class, Session.class);

}