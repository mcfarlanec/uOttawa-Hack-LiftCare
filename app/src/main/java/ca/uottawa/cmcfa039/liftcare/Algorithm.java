package ca.uottawa.cmcfa039.liftcare;
import java.util.ArrayList;

public class Algorithm{
	ArrayList<Request> emergent = new ArrayList<>();
	ArrayList<Request> urgent = new ArrayList<>();
	ArrayList<Request> scheduled = new ArrayList<>();
	ArrayList<Request> routine = new ArrayList<>();
	ArrayList<Request> ground = new ArrayList<>();

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

		int i;
		if (temp.isEmpty()){
			temp.add(request);
		} else {
			i = binarySearch(temp, patient, 0, temp.size());
			temp.add(i, request);
		}

		if (category == 1){
			emergent = temp;
		} else if (category == 2){
			urgent = temp;
		} else if (category == 3){
			scheduled = temp;
		} else if (category == 4){
			routine = temp;
		}

		masterList = new ArrayList<Request>();
		masterList.addAll(emergent);
		masterList.addAll(urgent);
		masterList.addAll(scheduled);
		masterList.addAll(routine);

		return masterList;
	}

	private int binarySearch(ArrayList<Request> list, Patient patient, int l, int r){
		int mid = 0;
		if (r >= 1) {
			mid = (l + (r-1))/2;

			if (list.get(mid).getPatient().getSeverity() == patient.getSeverity()){
				if (patient.getAge() > list.get(mid).getPatient().getAge()){
					return mid;
				} else {
					return mid + 1;
				}
			}

			if (list.get(mid).getPatient().getSeverity() > patient.getSeverity()){
				return binarySearch(list, patient, l, mid-1);
			}

			return binarySearch(list, patient, mid+1, r);
		}
		return mid;
	}

	//possible list of helicopters

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

	//next patient
}