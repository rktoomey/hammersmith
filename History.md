
0.1.0 / 2011-06-01 
==================

  * Fixes #28 - FindAndModify tests out, as does findAndRemove.  Also added Db/Coll level interfaces
  * In pretty good shape; need to iron out a bit more in findAndRemove but my battery has about 2 minutes left and next compile will nuke it.
  * Last few sets had some weird logical confusions from me being half alseep.  All works now although the rewriteID stuff is wonky and disabled.  Does it matter if _id isn't first?!?!?
  * Corrections to index creation behavior; still need an ensureindex cache
  * Count command
  * Implemented a batch insert, with insert now taking only a single doc per for sanity.  Batch Insert needs to also be tweaked to return a Seq[_id]
  * Fixes #27 - Some inverted logic and duplicate code loops was causing ObjectID insertion to skip and/or break
  * Now validating that writes succeeded by actually looking for the data int he database (novel idea, I know)
  * First layout of findAndModify
  * Migrated Specs to acceptance spec
  * CursorCleaner wasn't able to create consistent WeakRefs from call to call; switching to a WeakHashMap was the proper solution
  * Added an implicit conversion for "NOOP" Writes, (aka blind inserts) and a test of the behavior.
  * Refs #15, Fixes #7: Wired remaining code for writes     
    - Generate ID when it isn't there     
    - Validate keys     
    - Ensure getLastError semantics as a 'batch'
    - Callback to write if no safe mode    
    - Adjustments to MongoClientWriteMessage to have helper attributes
  * Added support for 'distinct'
  * Finished laying out find, createIndex, insert, update remove methods
  * Moved the Docs queue on Cursors to the new ConcurrentQueue implementation to ensure thread safety and avoid any blocking.
  * Refs #20 - Finished first pass at Cursor cleanup, with a custom ConcurrentQueue Scala API sitting on top of ConcurrentLinkedQueue.
  * Finished instrumenting remaining wire protocol messages with apply() objects
  * Refs #20 Laying the groundwork for Cursor Cleanup...    
    - Built out a timer infrastructure which reference counts registered ConnectionHandlers    
    - Unclean cursors which are cleaned or finalized are added to their handlers' dead cursors list     
    - When the scheduled callback clean runs, each handler is asked to clean its own dead cursors
  * Protected foreach method.
  * Fixed a stupid scoping and thus timing bug on callbacks.
  * Migrate Document / BSONDocument tree into an "org.bson.collection" area, likely to become Mutable / Immutable delineated at a later date.
  * Migrated base MongoDB package to com.mongodb.async
  * Added a getAsOrElse Method for castable orElse-ing
  * Switched to Either[Throwable, A] for results, provided a "SimpleRequestFutures" version which swallows exceptions.
  * Fixes #13 Defaults wired in, chain upwards to prior level using Option[WriteConcern]
  * Implemented Authentication.
  * Attempt to detect an empty queue condition WITHOUT an exception for a potential (perceived?)  performance boost.
  * Added an internal only 'basicIter' helper which is protected[mongodb] for default Iteration with Batch fetch behavior.
  * Iteratee Pattern now implemented for Cursors.  It made my brain hurt but it's a far cleaner methodology.
  * Store the request message with the future in a "CompletableRequest" in the dispatcher map
  * Settable Batch Size 
  * Added Twitter util-core for Future and Channels
  * Refs #11 
    - A few more utility methods on BSON Lists.  We need a constructor that takes an existing list, and implicits.
    - Saner implementation of BSONList, with an asList method to convert to a normal list
    - List Representation and cleanup of some of the base traits for Documents
  * Added in some of Casbah's Syntactic Sugar for BSONDocument
  * Allocate default dynamic buffer size on write to maxBSON if it's set, otherwise default of <1.8's 4 mb
  * Cleaned up BSON Size / isMaster check to use foreach
  * Netty's default assumes that the length is NOT inclusive of the header, while in BSON it is.  Needed to adjust frame length to -4.
  * For now, removing deserializer from SerializableBSONObject trait as it makes little sense until i lay that side of the API out
  * Deserializer structuring, similar PartialFunction syntax to the other
  * Swapped the writes of wire protocol to use the new BSONSerializer instead
  * Decoding framework with support for most Scala types baked in, use of partialfunction for decoding so you can customise in a subclass
  * Framework for basic connections via Netty
  * Wire Protocol messages laid out 
