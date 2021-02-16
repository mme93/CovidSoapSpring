package hrw.verteiltesysteme.CovidSoapSpring;

import generated.Covid;
import hrw.verteiltesysteme.CovidSoapSpring.covid.CalculateCovidNumber;
import hrw.verteiltesysteme.CovidSoapSpring.covid.JHU;
import hrw.verteiltesysteme.CovidSoapSpring.covid.RKI;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.emitter.ScalarAnalysis;


@Component
public class CovidSoapRepository {


    //nDays und rValue kann Null sein
    public Covid findEmployee(int nDays, String info, int rValue) {
        CalculateCovidNumber calculateCovidNumber = new CalculateCovidNumber(new RKI().getRKICovidInfo(), new JHU().getJHUCovidInfo());
        Covid covid;
        switch (info) {
            case "/date":
                covid = new Covid();
                covid.setJsonInfo(new JSONObject(calculateCovidNumber.getGermanyInfoJHU().get(calculateCovidNumber.getGermanyInfoJHU().size()-1).getDate()).toString());
                return covid;
            case "/infection":
                covid = new Covid();
                covid.setJsonInfo(calculateCovidNumber.getNewInfectionsLastDayJHU()+"");
                return covid;
            case "/infected":
                covid = new Covid();
                covid.setJsonInfo(calculateCovidNumber.getTotalInfectionsJHU()+"");
                return covid;
            case "/increase":
                covid = new Covid();
                //muss behoben werden
                covid.setJsonInfo(calculateCovidNumber.getIncreaseLasteDayJHU()+"");
            return covid;
            case "/average":
                covid = new Covid();
                covid.setJsonInfo(calculateCovidNumber.getAverageIncreaseDayJHU(7)+"");
                return covid;
            case "/incidencevalue":
                covid = new Covid();
                covid.setJsonInfo(calculateCovidNumber.getRWerthTotalGermanyRKI()+"");
                return covid;
            case "/incidencegoal":
                covid = new Covid();
                covid.setJsonInfo(calculateCovidNumber.getTotalTargetInfectionRKI(35)+"");
                return covid;
            case "/days":
                covid = new Covid();
                covid.setJsonInfo(calculateCovidNumber.getTargetIncidenceForRWerthRKI(35,calculateCovidNumber.getTotalInfectionsJHU(),7)+"");
                return covid;

            default:
                covid = new Covid();
                covid.setJsonInfo("Tut mir leid, diesen Befehl verstehe ich nicht.");
                return covid;
        }
    }
}