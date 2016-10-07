function openPage(hash) {
    
    var currLocation = location.pathname;
    var flag = 0;

    for (var i = currLocation.length - 1; i >= 0; i--) {
    if(currLocation[i] == "/" && flag != 1) {
      currLocation = currLocation.substr(0, i);
      flag = 1;
    }
  }
//  alert(currLocation + "/" + hash + ".html");
  window.location = currLocation + "/" + hash + ".html";
}

function deleteTask(event) {
    "use strict";
    var element = event.target;
    var first = element.parentNode;
    var second = first.parentNode;
    var third = second.parentNode;
    var fourth = third.parentNode;
    var fiveth = fourth.parentNode;
    $(fiveth).remove();
    
}

function addNewTask(currID, currProj, currName, startDate, finishDate, executor, progress) {
    
    var content = '<tr class="{progress_info}" id="tableRow"><td>' + currID + '</td><td>' + currProj + '</td><td>' + currName + '</td><td>' + startDate + '</td><td>' + finishDate + '</td><td>' + executor + '</td><td>' + progress + '</td><td><div class="row"><div class="col-xs-6"><p onclick="editTask(event)"><i class="fa fa-pencil" aria-hidden="true">  </i></p></div><div class="col-xs-6"><p onclick="deleteTask(event)"><i class="fa fa-eraser" aria-hidden="true">  </i></p></div></div></td></tr>';
    
    $(content).appendTo($('#table')); //?????
    
}

function editTask(event) {
    
    var element = event.target;
    var first = element.parentNode;
    var second = first.parentNode;
    var third = second.parentNode;
    var fourth = third.parentNode;
    var currentTask = fourth.parentNode;
    
    alert(currentTask.childNodes.length);
//    var currID = checkResultAndSetCookie( currentTask.getElementById("taskID").innerHTML);
//    
//    alert("((())");
    /*alert(
    currentTask.getElementsByTagName("tr").position);*/
}

/*function sendTaskToTable() {
    
    //alert($('#taskForm').serialize());
    var currID = checkResultAndSetCookie( document.getElementById("taskID").innerHTML);
    var currName = checkResultAndSetCookie( document.getElementById("taskName").innerHTML);
    var startDate = checkResultAndSetCookie( document.getElementById("startDate").value);
    var finishDate = checkResultAndSetCookie( document.getElementById("finishDate").value);
    var currProj = checkResultAndSetCookie($('#projectSelect option:selected').val());
    var executor = checkResultAndSetCookie($('#userSelect option:selected').val());
    var progress = checkResultAndSetCookie($('#progressSelect option:selected').val());
    
    openPage('userpanel');
    addNewTask(currID, currProj, currName, startDate, finishDate, executor, progress);
    
}

function checkResultAndSetCookie(name, result) {
    if (result != "") {
        return setCookie(name, result);
    } else {
        return setCookie(name, "Not set");
    }
}*/

/*function setSessionValue(name, value) {
  var cookie = name + "=" + escape(value) + ";";

  if (expires) {
    // If it's a date
    if(expires instanceof Date) {
      // If it isn't a valid date
      if (isNaN(expires.getTime()))
       expires = new Date();
    }
    else
      expires = new Date(new Date().getTime() + parseInt(expires) * 1000 * 60 * 60 * 24);

    cookie += "expires=" + expires.toGMTString() + ";";
  }

  if (path)
    cookie += "path=" + path + ";";
  if (domain)
    cookie += "domain=" + domain + ";";

  document.cookie = cookie;
}

function getCookie(name) {
  var regexp = new RegExp("(?:^" + name + "|;\s*"+ name + ")=(.*?)(?:;|$)", "g");
  var result = regexp.exec(document.cookie);
    alert(result);
  return (result === null) ? null : result[1]; 
}

function loadTaskPage() {
    
    var taskName = getCookie("{taskname_from_prepage}");
    if (taskName != null) {
        document.getElementById("taskName").innerHTML = taskName;
    } else {
        document.getElementById("taskName").innerHTML = "NewTask";
    }
    
}*/

