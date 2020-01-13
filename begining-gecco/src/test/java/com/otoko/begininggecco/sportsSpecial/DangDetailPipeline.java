package com.otoko.begininggecco.sportsSpecial;

import com.otoko.begininggecco.annotation.PipelineName;
import com.otoko.begininggecco.pipeline.Pipeline;
import com.otoko.begininggecco.begininggeccoApplicationTests;

@PipelineName("sportsSpecialDetailPipeline")
public class DangDetailPipeline implements Pipeline<DangDetail>{

    @Override
    public void process(DangDetail dangDetail) {
        begininggeccoApplicationTests.insertSportsSpecial(dangDetail);
    }
}
