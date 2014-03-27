package Holders;

import java.util.ArrayList;
import java.util.List;

public class VisitObject {

	String objectName;
	String categoryId;
	String objectDescription;
	String shortDescription;
	List<WorkingHours> hours;

	List<Groups> groups;

	public VisitObject(String objectName, String categoryId,
			String objectDescription, String shortDescription) {
		super();
		this.objectName = objectName;
		this.categoryId = categoryId;
		this.objectDescription = objectDescription;
		this.shortDescription = shortDescription;
		hours = new ArrayList<>();
		groups = new ArrayList<>();
	}

	
	public String getObjectName() {
		return objectName;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public String getObjectDescription() {
		return objectDescription;
	}

	public List<WorkingHours> getHours() {
		return hours;
	}

	public List<Groups> getGroups() {
		return groups;
	}



	public class WorkingHours {
		String day, hours;

		public WorkingHours(String day, String hours) {
			this.day = day;
			this.hours = hours;
		}

		public String getDay() {
			return day;
		}

		public String getHours() {
			return hours;
		}

		@Override
		public String toString() {
			return "WorkingHours [day=" + day + ", hours=" + hours + "]";
		}
		
		
	}

	public class Groups {
		String group, price;

		public Groups(String group, String price) {
			this.group = group;
			this.price = price;
		}

		public String getGroup() {
			return group;
		}

		public String getPrice() {
			return price;
		}

		@Override
		public String toString() {
			return "Groups [group=" + group + ", price=" + price + "]";
		}
		
		
	}

	public void addWorkingHrs(WorkingHours workingHours) {
		hours.add(workingHours);

	}

	public void addGroup(Groups groups2) {
		groups.add(groups2);

	}


	public String getShortDescription() {
		// TODO Auto-generated method stub
		return shortDescription;
	}


	@Override
	public String toString() {
		return "VisitObject [objectName=" + objectName + ", categoryId="
				+ categoryId + ", objectDescription=" + objectDescription
				+ ", shortDescription=" + shortDescription + ", hours=" + hours
				+ ", groups=" + groups + "]";
	}
	
	
}
