import sbt._
import growl._
import com.github.olim7t.sbtscalariform._

class HammersmithProject(info: ProjectInfo)
  extends ParentProject(info) 
  with IdeaProject
  with posterous.Publish {

  override def managedStyle = ManagedStyle.Maven

  lazy val bson = project("bson-driver", "bson-driver", new BSONDriverProject(_))
  lazy val mongo = project("mongo-driver", "mongo-driver", new MongoDriverProject(_), bson)


  abstract class HammersmithBaseProject(info: ProjectInfo) 
    extends DefaultProject(info)
    with AutoCompilerPlugins
    with IdeaProject
    with GrowlingTests {

    //override def scalariformOptions = Seq(VerboseScalariform)


    // Use the BSON code
//    val jBSON = "org.mongodb" % "bson" % "2.5.2"
    val casbah = "com.mongodb.casbah" %% "casbah-util" % "2.2.0-SNAPSHOT"
    // Connection Pooling
    val commonsPool = "commons-pool" % "commons-pool" % "1.5.5"

    val scalaj_collection = "org.scalaj" %% "scalaj-collection" % "1.1"
    // Netty
    val netty = "org.jboss.netty" % "netty" % "3.2.4.Final"

    // Twitter-util
    val twitterUtilCore = "com.twitter" % "util-core" % "1.8.13"

    // Testing Deps
    val specs2 = "org.specs2" %% "specs2" % "1.4"  
    //val scalaz = "org.specs2" %% "specs2-scalaz-core" % "6.0.RC2"  

    def specs2Framework = new TestFramework("org.specs2.runner.SpecsFramework")
    override def testFrameworks = super.testFrameworks ++ Seq(specs2Framework)

    
  }


  class BSONDriverProject(info: ProjectInfo) extends HammersmithBaseProject(info) {
    // For testing BSON wire formats etc from a 'known good' state
    val mongoJava = "org.mongodb" % "mongo-java-driver" % "2.6.2" % "test->default"
  }

  class MongoDriverProject(info: ProjectInfo) extends HammersmithBaseProject(info) {

    //val slf4jJCL = "org.slf4j" % "slf4j-simple" % "1.6.1" % "test"
  }

  val slf4j = "org.slf4j" % "slf4j-api" % "1.6.1"
  val logback = "ch.qos.logback" % "logback-classic" % "0.9.28"

  val sbtSnapshots = "snapshots" at "http://scala-tools.org/repo-snapshots"
  val sbtReleases  = "releases" at "http://scala-tools.org/repo-releases"

  val jbossRepo = "JBoss Public Repo" at "https://repository.jboss.org/nexus/content/groups/public-jboss/"
  val twttrRepo = "Twitter Public Repo" at "http://maven.twttr.com"
}

// vim: set ts=2 sw=2 sts=2 et:
