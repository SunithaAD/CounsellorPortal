package in.ashokit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Counsellor;
import in.ashokit.repo.CounsellorRepository;
@Service
public class CounsellorServiceImpl implements CounsellorService {
	
	@Autowired
    private CounsellorRepository counsellorRepo;
	
	@Override
	public boolean saveCounsellor(Counsellor counsellor) {
		Counsellor findByEmail = counsellorRepo.findByEmail(counsellor.getEmail());
		
		if(findByEmail!=null)
				{
				return false;
			
				}
		else
		{
			Counsellor savedCounsellor = counsellorRepo.save(counsellor);
		
		
		
		return savedCounsellor.getCounsellorId()!=null;
	}
	}
	@Override
	public Counsellor getCounsellor(String email, String pwd) {
		
		return counsellorRepo.findByEmailAndPwd(email,pwd);
	}

}
