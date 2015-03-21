package ghumover2
import grails.rest.Resource

@Resource

class Guardian extends User {

    String name
    String educational_qualification
    String profession
    String designation
    String mobile_number
    String email_id
    String office_number
    static hasMany = [children:Student]

    static constraints = {

        educational_qualification(nullable: true)
        profession(nullable: true)
        designation(nullable: true)
        mobile_number(nullable: true)
        email_id(nullable: true)
        office_number(nullable: true)
    }
}
