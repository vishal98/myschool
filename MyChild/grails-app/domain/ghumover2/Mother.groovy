package ghumover2

class Mother extends Guardian {

    static hasMany = [children:Student]
    static constraints = {
    }
}
