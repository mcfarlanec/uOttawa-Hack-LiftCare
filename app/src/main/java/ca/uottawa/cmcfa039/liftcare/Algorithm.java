package ca.uottawa.cmcfa039.liftcare;
import java.util.ArrayList;

public class Algorithm{
	ArrayList<Request> emergent = new ArrayList<>();
	ArrayList<Request> urgent = new ArrayList<>();
	ArrayList<Request> scheduled = new ArrayList<>();
	ArrayList<Request> routine = new ArrayList<>();

	ArrayList<Request> masterList = new ArrayList<>();

	public ArrayList<Request> receiveRequest(Request request){
		ArrayList<Request> temp = new ArrayList<>();
		Patient patient = request.getPatient();

		int category = patient.getCategory();
		if (category == 1){
			temp = emergent;
		} else if (category == 2){
			temp = urgent;
		} else if (category == 3){
			temp = scheduled;
		} else if (category == 4){
			temp = routine;
		} else {
			return masterList;
		}

		int index = 0;
		if (temp.isEmpty()){
			temp.add(0,request);
		} else {
			for (int i = 0; i < temp.size() -1; i ++){
				if (temp.get(i).getPatient().getSeverity() == patient.getSeverity()){
					if (patient.getAge() > temp.get(i).getPatient().getAge()){
						index = i;
					} else {
						index = i + 1;
					}
					break;
				} else if (temp.get(i).getPatient().getSeverity() < patient.getSeverity() && temp.get(i+1).getPatient().getSeverity() > patient.getSeverity()) {
					index = i;
					break;
				} else {
					index = i;
				}
			}
			temp.add(index, request);
		}

		if (category == 1){
			emergent = temp;
		} else if (category == 2){
			urgent = temp;
		} else if (category == 3){
			scheduled = temp;
		} else {
			routine = temp;
		}

		masterList = new ArrayList<>();
		masterList.addAll(emergent);
		masterList.addAll(urgent);
		masterList.addAll(scheduled);
		masterList.addAll(routine);

		return masterList;
	}

	//remove/patient transported function
	private void removeRequest(Request request){
		int category = request.getPatient().getCategory();
		if (category == 1){
			emergent.remove(request);
		} else if (category == 2){
			urgent.remove(request);
		} else if (category == 3){
			scheduled.remove(request);
		} else if (category == 4){
			routine.remove(request);
		}
		masterList.remove(request);
	}
}