package laudhoot.core.services;

import java.util.ArrayList;
import java.util.List;

import laudhoot.core.domain.FAQ;
import laudhoot.core.repository.FAQRepository;
import laudhoot.web.domain.FAQTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FAQServiceImpl implements FAQService {

	@Autowired
	FAQRepository faqRepository;
	
	@Override
	public List<FAQTO> getFAQs(Integer faqsAvailable) {
		List<FAQTO> faqs = new ArrayList<FAQTO>();
		for (FAQ faq : faqRepository.findAll()) {
			faqs.add(new FAQTO(faq));
		}
		if (faqs != null && faqsAvailable < faqs.size()) {
			faqs = faqs.subList(faqsAvailable, faqs.size());
			if (faqs.size() > 10) {
				return faqs.subList(faqsAvailable, faqsAvailable + 10);
			} else {
				return faqs;
			}
		}
		return null;
	}
	
}
