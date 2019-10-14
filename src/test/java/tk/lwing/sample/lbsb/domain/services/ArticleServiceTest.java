package tk.lwing.sample.lbsb.domain.services;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import tk.lwing.sample.lbsb.domain.repositories.ArticleRepository;

@RunWith(SpringRunner.class)
public class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;
    @InjectMocks
    private ArticleService articleService;
}
