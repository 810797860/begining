package com.otoko.begininggecco.electricAppliance;

import com.otoko.begininggecco.annotation.PipelineName;
import com.otoko.begininggecco.pipeline.Pipeline;
import com.otoko.begininggecco.begininggeccoApplicationTests;

@PipelineName("electricApplianceDetailPipeline")
public class DangDetailPipeline implements Pipeline<DangDetail>{

    @Override
    public void process(DangDetail dangDetail) {
        begininggeccoApplicationTests.insertElectricAppliance(dangDetail);
    }
}
