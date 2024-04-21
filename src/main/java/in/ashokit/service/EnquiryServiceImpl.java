package in.ashokit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Counsellor;
import in.ashokit.entity.Enquiry;
import in.ashokit.dto.Dashboard;
import in.ashokit.repo.CounsellorRepository;
import in.ashokit.repo.EnquiryRepository;
@Service
public class EnquiryServiceImpl implements EnquiryService {
	
	@Autowired
     private EnquiryRepository enqRepo;

	@Autowired
     private CounsellorRepository counsellorRepo;
	
	@Override
	public Dashboard getDashboardInfo(Integer counsellorId) {
		
		Long totalEnq = enqRepo.getEnquiries(counsellorId);
		Long openCnt = enqRepo.getEnquiries(counsellorId, "Open");
		Long lostCnt = enqRepo.getEnquiries(counsellorId, "Lost");
		Long enrolledCnt = enqRepo.getEnquiries(counsellorId, "Enrolled");
		
		Dashboard dashboard = new Dashboard();
		dashboard.setTotalEnqs(totalEnq);
		dashboard.setLostEnqs(lostCnt);
		dashboard.setOpenEnqs(openCnt);
		dashboard.setEnrolledEnqs(enrolledCnt);
		return dashboard;
		}

	@Override
	public boolean addEnquiry(Enquiry enquiry,Integer counsellorId) {
		
		Counsellor counsellor = counsellorRepo.findById(counsellorId).orElseThrow();
		
		enquiry.setCounsellor(counsellor);//Association for fk
		
		Enquiry savedEnq = enqRepo.save(enquiry);
		
		return savedEnq.getEnqId()!=null;
	}

	@Override
	public List<Enquiry> getEnquiries(Enquiry enquiry, Integer counsellorId) {
		
      //Counsellor counsellor = counsellorRepo.findById(counsellorId).orElseThrow();
		
		//enquiry.setCounsellor(counsellor);
		
		Counsellor counsellor =  new Counsellor();
		counsellor.setCounsellorId(counsellorId);
		
		//Adding Filter values to entity
		Enquiry searchCriteria =  new Enquiry();
		searchCriteria.setCounsellor(counsellor);
		
		if(null!=enquiry.getCourse()&&!"".equals(enquiry.getCourse()))
		{searchCriteria.setCourse(enquiry.getCourse());
		
		}
		
		
		if(null!=enquiry.getMode()&&!"".equals(enquiry.getMode()))
		{searchCriteria.setMode(enquiry.getMode());
		
		}
		
		
		if(null!=enquiry.getStatus()&&!"".equals(enquiry.getStatus()))
		{searchCriteria.setStatus(enquiry.getStatus());
		
		}
				
		
		//dynamic Query Creation
		Example<Enquiry> of= Example.of(searchCriteria);
		return enqRepo.findAll(of);
	}

	@Override
	public Enquiry getEnquiry(Integer enqId) {
		return enqRepo.findById(enqId).orElseThrow();
	}

	

}
