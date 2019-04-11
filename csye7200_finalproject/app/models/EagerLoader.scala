package models

import com.google.inject.AbstractModule
import controllers.datasetProcess

/**
  * @project CSYE7200_FinalProject
  * @author
  */
class EagerLoader extends AbstractModule{
  override def configure(): Unit = {
    bind(classOf[datasetProcess]).asEagerSingleton()
  }
}
