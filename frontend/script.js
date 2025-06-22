const chatBox = document.getElementById("chat");
const chatForm = document.getElementById("chatForm");
const userInput = document.getElementById("userInput");
const history = document.getElementById("history");

chatForm.addEventListener("submit", async (e) => {
  e.preventDefault();
  const prompt = userInput.value.trim();
  if (!prompt) return;

  addMessage(prompt, "user");
  userInput.value = "";

  const responseDiv = addMessage("Thinking...", "bot");

  try {
    const res = await fetch("http://localhost:8081/askme?prompt=" + encodeURIComponent(prompt));
    const data = await res.text();

    responseDiv.textContent = data;
    saveToHistory(prompt);
  } catch (error) {
    responseDiv.textContent = "Error: " + error.message;
  }
});

function addMessage(text, role) {
  const msg = document.createElement("div");
  msg.className = `message ${role}`;
  msg.textContent = text;
  chatBox.appendChild(msg);
  chatBox.scrollTop = chatBox.scrollHeight;
  return msg;
}

function saveToHistory(prompt) {
  const item = document.createElement("li");
  item.textContent = prompt;
  item.onclick = () => userInput.value = prompt;
  history.appendChild(item);
}

// Theme toggle
function toggleTheme() {
  document.body.classList.toggle("dark");
  document.querySelector(".mode-toggle button").textContent =
    document.body.classList.contains("dark") ? "Light mode" : "Dark mode";
}
