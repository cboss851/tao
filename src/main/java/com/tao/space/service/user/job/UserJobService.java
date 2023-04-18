package com.tao.space.service.user.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author：cboss
 * @Date：2023/3/30
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserJobService {
    @XxlJob("userSync")
    public ReturnT<String> userSync() {
        log.info("userJob 任务开始");
        return ReturnT.SUCCESS;
    }
}
