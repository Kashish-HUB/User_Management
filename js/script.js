// This is base url for backend request 
const API = "http://localhost:8080/api/users";    

// Format date and time in pattern like "Jul 05, 2025, 12:30 PM".
const dateFormat = {                 
  year: "numeric",
  month: "short",
  day: "2-digit",
  hour: "2-digit",
  minute: "2-digit"
};

//---REFERENCES FROM CLIENT-----
const userForm     = document.getElementById("userForm");
const userIdField  = document.getElementById("userId");
const nameField    = document.getElementById("name");
const emailField   = document.getElementById("email");
const nameError    = document.getElementById("nameError");
const emailError   = document.getElementById("emailError");
const resetBtn     = document.getElementById("resetFormbtn");
const msgBox       = document.getElementById("messageBox");
const tableBody    = document.getElementById("userTableBody");

// Utility: Format ISO date
const fmtDate = iso =>
  iso ? new Date(iso).toLocaleString(undefined, dateFormat) : "-";

// Show message
function showMessage(type, text, ms = 3500) {
  msgBox.textContent = text;
  msgBox.className = `message-box ${type}`; // success | error
  msgBox.style.display = "block";
  clearTimeout(msgBox._timer);
  msgBox._timer = setTimeout(() => (msgBox.style.display = "none"), ms);
}

// Clear form
function clearForm() {
  userForm.reset();
  userIdField.value = "";
  [nameError, emailError].forEach(el => (el.textContent = ""));
}

// Render one row
function rowHTML(u) {
  return `
    <tr data-id="${u.id}">
      <td data-label="ID">${u.id}</td>
      <td data-label="Name">${u.name}</td>
      <td data-label="Email">${u.email}</td>
      <td data-label="Created At">${fmtDate(u.createdAt)}</td>
      <td data-label="Updated At">${fmtDate(u.updatedAt)}</td>
      <td data-label="Actions">
        <button class="action-btn edit">Edit</button>
        <button class="action-btn delete">Delete</button>
      </td>
    </tr>`;
}

// Fetch all users (updated for paginated API)
async function refreshUsers() {
  tableBody.innerHTML = `<tr><td colspan="6" style="text-align:center;padding:10px;">Loading…</td></tr>`;
  try {
    const response = await fetch(`${API}/page?page=0&size=100`);
    if (!response.ok) throw new Error("Failed to fetch users");

    const result = await response.json();
    const list = result.content; // paginated content

    tableBody.innerHTML = list.length
      ? list.map(rowHTML).join("")
      : `<tr><td colspan="6" style="text-align:center;padding:10px;">No users yet</td></tr>`;
  } catch (err) {
    showMessage("error", "Failed to load users 😢");
    console.error(err);
  }
}

// Add or update
userForm.addEventListener("submit", async e => {
  e.preventDefault();

  [nameError, emailError].forEach(el => (el.textContent = ""));
  if (!nameField.value.trim()) {
    nameError.textContent = "Name is required";
    return;
  }
  if (!emailField.value.trim()) {
    emailError.textContent = "Email is required";
    return;
  }

  const payload = [{
    name: nameField.value.trim(),
    email: emailField.value.trim()
  }];

  const id = userIdField.value;
  const url = id ? `${API}/${id}` : API;
  const method = id ? "PUT" : "POST";
  const body = id ? JSON.stringify(payload[0]) : JSON.stringify(payload);

  try {
    const res = await fetch(url, {
      method,
      headers: { "Content-Type": "application/json" },
      body
    });

    if (!res.ok) throw new Error(await res.text());
    showMessage("success", `User ${id ? "updated" : "created"} successfully!`);
    clearForm();
    refreshUsers();
  } catch (err) {
    showMessage("error", err.message || "Something went wrong");
  }
});

// Reset form
resetBtn.addEventListener("click", clearForm);

// Edit/Delete handler
tableBody.addEventListener("click", async e => {
  const btn = e.target.closest("button");
  if (!btn) return;

  const tr = btn.closest("tr");
  const id = tr.dataset.id;

  if (btn.classList.contains("edit")) {
    const cells = tr.children;
    userIdField.value = id;
    nameField.value = cells[1].textContent;
    emailField.value = cells[2].textContent;
    window.scrollTo({ top: 0, behavior: "smooth" });
  }

  if (btn.classList.contains("delete")) {
    if (!confirm("Delete this user?")) return;
    try {
      const res = await fetch(`${API}/${id}`, { method: "DELETE" });
      if (!res.ok) throw new Error(await res.text());
      showMessage("success", "User deleted");
      refreshUsers();
    } catch (err) {
      showMessage("error", err.message || "Delete failed");
    }
  }
});

// Load users on page load
document.addEventListener("DOMContentLoaded", refreshUsers);
