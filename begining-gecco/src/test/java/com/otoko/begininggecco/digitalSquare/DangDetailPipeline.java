package com.otoko.begininggecco.digitalSquare;

import com.otoko.begininggecco.annotation.PipelineName;
import com.otoko.begininggecco.pipeline.Pipeline;
import com.otoko.begininggecco.begininggeccoApplicationTests;

@PipelineName("digitalSquareDetailPipeline")
public class DangDetailPipeline implements Pipeline<DangDetail>{

    @Override
    public void process(DangDetail dangDetail) {
        begininggeccoApplicationTests.insertDigitalSquare(dangDetail);
    }
}
