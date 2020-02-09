package ca.uottawa.cmcfa039.liftcare;
import java.util.ArrayList;

public class Algorithm{
	ArrayList<Patient> emergent = new ArrayList<Patient>();
	ArrayList<Patient> urgent = new ArrayList<Patient>();
	ArrayList<Patient> scheduled = new ArrayList<Patient>();
	ArrayList<Patient> routine = new ArrayList<Patient>();
	ArrayList<Patient> ground = new ArrayList<Patient>();

	ArrayList<Patient> masterList = new ArrayList<Patient>();

	private ArrayList<Patient> receiveRequest(Request request){
		ArrayList<Patient> temp = new ArrayList<Patient>();
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
			temp.add(patient);
		} else {
			i = binarySearch(temp, patient, 0, temp.size());
			temp.add(i, patient);
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

		masterList = new ArrayList<Patient>();
		masterList.addAll(emergent);
		masterList.addAll(urgent);
		masterList.addAll(scheduled);
		masterList.addAll(routine);

		return masterList;
	}

	private int binarySearch(ArrayList<Patient> list, Patient patient, int l, int r){
		int mid = 0;
		if (r >= 1) {
			mid = (l + (r-1))/2;

			if (list.get(mid).getSeverity() == patient.getSeverity()){
				if (patient.getAge() > list.get(mid).getAge()){
					return mid;
				} else {
					return mid + 1;
				}
			}

			if (list.get(mid).getSeverity() > patient.getSeverity()){
				return binarySearch(list, patient, l, mid-1);
			}

			return binarySearch(list, patient, mid+1, r);
		}
		return mid;
	}

	//possible list of helicopters

	//remove/patient transported function
	private void removePatient(Patient patient){
		int category = patient.getCategory();
		if (category == 1){
			emergent.remove(patient);
		} else if (category == 2){
			urgent.remove(patient);
		} else if (category == 3){
			scheduled.remove(patient);
		} else if (category == 4){
			routine.remove(patient);
		}
		masterList.remove(patient);
	}

	//next patient
}