package spring;

import java.util.Date;

import javax.annotation.Resource;

public class MemberRegisterService {
	@Resource(name="memberDao")
	private MemberDao memberDao;

	//@Autowired 생성자에는 적용불가
	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public void regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		if (member != null) {
			throw new AlreadyExistingMemberException("dup email " + req.getEmail());
		}
		Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName(), new Date());
		memberDao.insert(newMember);
	}
}
