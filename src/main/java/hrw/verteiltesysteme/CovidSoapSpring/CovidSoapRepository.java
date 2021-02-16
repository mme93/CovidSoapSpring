package hrw.verteiltesysteme.CovidSoapSpring;

import generated.Covid;
import hrw.verteiltesysteme.CovidSoapSpring.covid.CalculateCovidNumber;
import hrw.verteiltesysteme.CovidSoapSpring.covid.JHU;
import hrw.verteiltesysteme.CovidSoapSpring.covid.RKI;
import org.json.JSONObject;
import org.springframework.stereotype.Component;


@Component
public class CovidSoapRepository {


    //nDays und rValue kann Null sein
    public Covid findCovid(int nDays, String info, int rValue) {
        CalculateCovidNumber calculateCovidNumber = new CalculateCovidNumber(new RKI().getRKICovidInfo(), new JHU().getJHUCovidInfo());
        Covid covid;
        switch (info) {
            case "/date":
                covid = new Covid();
                covid.setJsonInfo(new JSONObject().put("value",calculateCovidNumber.getGermanyInfoJHU().get(calculateCovidNumber.getGermanyInfoJHU().size()-1).getDate()).toString());
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
                covid.setJsonInfo(calculateCovidNumber.getIncreaseLasteDayJHU()+"");
            return covid;
            case "/average":
                covid = new Covid();
                covid.setJsonInfo(calculateCovidNumber.getAverageIncreaseDayJHU(nDays)+"");
                return covid;
            case "/incidencevalue":
                covid = new Covid();
                covid.setJsonInfo(calculateCovidNumber.getRWerthTotalGermanyRKI()+"");
                return covid;
            case "/incidencegoal":
                covid = new Covid();
                covid.setJsonInfo(calculateCovidNumber.getTotalTargetInfectionRKI(rValue)+"");
                return covid;
            case "/days":
                covid = new Covid();
                covid.setJsonInfo(calculateCovidNumber.getTargetIncidenceForRWerthRKI(rValue,calculateCovidNumber.getTotalInfectionsJHU(),nDays)+"");
                return covid;

            default:
                covid = new Covid();
                covid.setJsonInfo("Tut mir leid, diesen Befehl verstehe ich nicht.");
                return covid;
        }
    }
}