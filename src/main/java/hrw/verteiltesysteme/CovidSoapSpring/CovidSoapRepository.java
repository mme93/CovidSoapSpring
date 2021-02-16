package hrw.verteiltesysteme.CovidSoapSpring;

import generated.Covid;
import hrw.verteiltesysteme.CovidSoapSpring.covid.CalculateCovidNumber;
import hrw.verteiltesysteme.CovidSoapSpring.covid.JHU;
import hrw.verteiltesysteme.CovidSoapSpring.covid.RKI;
import org.json.JSONObject;
import org.springframework.stereotype.Component;


@Component
public class CovidSoapRepository {



    public Covid findEmployee(int nDays, String info, int rValue) {
        CalculateCovidNumber calculateCovidNumber = new CalculateCovidNumber(new RKI().getRKICovidInfo(), new JHU().getJHUCovidInfo());
        switch (info) {
            case "/date":
                Covid covid = new Covid();
                covid.setJsonInfo(new JSONObject(calculateCovidNumber.getGermanyInfoJHU().get(calculateCovidNumber.getGermanyInfoJHU().size()-1).getDate()).toString());
                return covid;   /*
                case "/infection":
                new Covid().setJsonInfo(new JSONObject(calculateCovidNumber.getGermanyInfoJHU().get(calculateCovidNumber.getGermanyInfoJHU().size()-1).getDate().toString());
                return "Es gab " + new JSONObject(request.getNewInfection()).get("value")
                        + " Neuinfektionen in den letzten 24 Stunden.";
            case "/infected":
                return "Die Gesamtcoronainfektionen liegen bei " + new JSONObject(request.getTotalInfection()).get("value")
                        + " Menschen.";
            case "/increase":
                return "Der prozentuale Anstieg der letzten 24 Stunden liegt bei "
                        + new JSONObject(request.getPercenteInfection()).get("value") + "%.";
            case "/average":
                return "Der Anstieg in den letzten 7 Tagen beträgt "
                        + new JSONObject(request.getAverageIncreaseDay(7)).get("value");
            case "/incidencevalue":
                return "Der Inzidenzwert für Deutschland liegt aktuell bei: "
                        + new JSONObject(request.getRWerthTotalGermany()).get("value");
            case "/incidencegoal":
                return "Der Ziel-Inzidenzwert ist " + new JSONObject(request.getTotalTargetInfection(35)).get("value")
                        + ".";
            case "/days":
                return "Es dauert aktuell " + new JSONObject(request.getTargetIncidenceForRWert(35, 7)).get("value")
                        + "Tage um den Ziel-Inzidenzwert zu erreichen.";
            */
            default:
                Covid s = new Covid();
                s.setJsonInfo("Tut mir leid, diesen Befehl verstehe ich nicht.");
                return s;
        }
    }
}