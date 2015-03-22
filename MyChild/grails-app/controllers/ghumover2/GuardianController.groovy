package ghumover2
import grails.rest.RestfulController


class GuardianController extends RestfulController {
    static responseFormats = ['json', 'xml']
    GuardianController() {
        super(Guardian)
    }


}