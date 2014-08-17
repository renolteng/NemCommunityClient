
The following API list is not yet complete and will likely be changed during implementation. The web-context is defined as “/ncc/api” (full URI would be, for instance,  “/ncc/api/client/info”)

Amount values are all in the smallest possible NEM fraction, that means that 1000000 means 1.000000 NEM.

Rest calls must have the content type set:

`Content-Type: application/json`

***

### /info/ncc
Gets information about the NCC server.

* Request Method: _GET_
* Request: `-`
* Response: [NisInfoViewModel](viewModel.md#nisinfoviewmodel)
* Dependent from NIS: _No_

***

### /info/nis
Gets information about the NIS server.

* Request Method: _GET_
* Request: `-`
* Response: [NisInfoViewModel](viewModel.md#nisinfoviewmodel)
* Dependent from NIS: _Yes_

***

### /info/nis/check
Checks availability of NIS server.

* Request Method: _GET_
* Request: `-`
* Response:

** 0 - NIS server is not running
** 1 - NIS server is running (but may not be booted)

* Dependent from NIS: _Yes_
