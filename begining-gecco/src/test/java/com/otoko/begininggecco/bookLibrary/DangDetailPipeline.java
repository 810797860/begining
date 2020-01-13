package com.otoko.begininggecco.bookLibrary;

import com.otoko.begininggecco.annotation.PipelineName;
import com.otoko.begininggecco.pipeline.Pipeline;
import com.otoko.begininggecco.begininggeccoApplicationTests;

@PipelineName("bookLibraryDetailPipeline")
public class DangDetailPipeline implements Pipeline<DangDetail>{

    @Override
    public void process(DangDetail dangDetail) {
        begininggeccoApplicationTests.insertBookLibrary(dangDetail);
    }
}
