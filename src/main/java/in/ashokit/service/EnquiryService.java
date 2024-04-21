package in.ashokit.service;

import java.util.List;

import in.ashokit.entity.Enquiry;
import in.ashokit.dto.Dashboard;

public interface EnquiryService {
	
	//save Enquiry
	public boolean addEnquiry(Enquiry enquiry,Integer counsellorId);
	
	//view enquiries+filter
	public List<Enquiry> getEnquiries(Enquiry enquiry, Integer counsellorId);
	
	//edit
	public Enquiry getEnquiry(Integer enqId);
	
	//for dashboard page
	public Dashboard getDashboardInfo(Integer counsellorId);

}
